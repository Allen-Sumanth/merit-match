from operator import and_
from typing import Annotated, Optional
from fastapi import Depends, FastAPI, status, HTTPException
from sqlalchemy import null, or_
from sqlalchemy.orm import Session
import models
from dbconfig import engine
from pwdencryption import hasher, authenticator
from dbconfig import SessionLocal
from schemas.users import User
from schemas.task import NewTask, TaskUpdate, UpdateTaskStatus

app = FastAPI()
models.Base.metadata.create_all(bind = engine)

def get_db():
    db = SessionLocal()
    try:
        yield db
    finally:
        db.close()

db_dependency = Annotated[Session, Depends(get_db)]

#api endpoints
@app.get("/check-account")
async def check_existing_account(username:str, db: db_dependency):
    acc_deets = db.query(models.Users).filter(models.Users.username == username).first()
    if acc_deets is None:
        return {"exists": "false"}
    else:
        return {"exists": "true"}

@app.post("/create-account", status_code=status.HTTP_201_CREATED)
async def create_account(user: User, db: db_dependency):
    user.password = hasher(user.password)
    db_user = models.Users(**user.model_dump())
    db.add(db_user)
    db.commit()
    return {"status": "Success"}

@app.post("/login")
async def login_user(user: User, db: db_dependency):

    get_user = db.query(models.Users).filter(models.Users.username == user.username).first()

    loginStatus = authenticator(hash = getattr(get_user, 'password'), password = user.password)

    if loginStatus is False:
        return {"login": "failure"}
    elif loginStatus is True: 
        return {"login": "success"}
    else: 
        return {"login": "error"}


@app.get("/fetchkarma/{username}")
async def fetch_karma(username: str, db: db_dependency):
    user_details = db.query(models.Users).filter(models.Users.username == username).first()
    karma = getattr(user_details, 'karma')
    return {"karma": karma}

@app.put("/deductkarma/{username}/{karma}")
async def deduct_karma(username: str, karma: int, db:db_dependency):
    rowcount = 0
    rowcount = db.query(models.Users).filter(
        models.Users.username == username).update(
            {models.Users.karma: models.Users.karma - karma})
    db.commit()

    if rowcount == 1: return {"message": "success"}
    else: return {"message": "failiure"}

@app.get("/fetchreserved/{username}")
async def fetch_reserved_tasks(username: str, db: db_dependency):
    task_list = db.query(models.Tasks).filter(models.Tasks.reservedby == username,or_(models.Tasks.task_status == 'RESERVED', models.Tasks.task_status == 'COMPLETED')).all()
    if task_list == []:
        return []
    return task_list

@app.get("/fetchnewtasks/{username}")
async def fetch_new_tasks(*, username: str, count: Optional[int] = 5, db:db_dependency):
    task_list = db.query(models.Tasks).filter(models.Tasks.username != username, models.Tasks.task_status == 'NEW').limit(count).all()
    if task_list is None:
        raise HTTPException(status_code=404, detail='tasks not found')
    return task_list

@app.post("/addnewtask")
async def add_new_task(task_content: NewTask, db: db_dependency):
    new_task = models.Tasks(**task_content.model_dump())
    db.add(new_task)
    db.query(models.Users).filter(models.Users.username == getattr(task_content, 'username')).update({
        models.Users.karma: models.Users.karma - getattr(task_content, 'karma')
    })
    db.commit()

@app.put("/updatetaskstatus/{username}")
async def update_task_status(username: str, task_details: UpdateTaskStatus, db:db_dependency):
    if (task_details.task_status == "RESERVED" or task_details.task_status == "COMPLETED"):
        reserved_user = username
    else:
        reserved_user = None
    rowcount = 0
    rowcount = db.query(models.Tasks).filter(
        models.Tasks.taskid == task_details.taskid).update(
            {models.Tasks.task_status: task_details.task_status,
              models.Tasks.reserved_date: task_details.reserved_date,
              models.Tasks.completion_date: task_details.completion_date,
              models.Tasks.closing_date: task_details.closing_date,
              models.Tasks.reservedby: reserved_user})
    db.commit()

    if rowcount == 1: return {"message": "success"}
    else: return {"message": "failiure"}

@app.get("/fetchusertasks/{username}")
async def fetch_user_tasks(username: str, db: db_dependency):
    task_list = db.query(models.Tasks).filter(models.Tasks.username == username).all()
    if task_list is None:
        return {"error": "no tasks found"}
    
    return task_list