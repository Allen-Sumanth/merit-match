from pydantic import BaseModel
from typing import Optional
from datetime import date

class NewTask(BaseModel):
    username: str
    task_title: str
    task_desc: str
    karma: int
    task_status: str #NEW, RESERVED, COMPLETED, CLOSED
    assign_date: date

class UpdateTaskStatus(BaseModel):
    taskid: int
    task_status: str
    reserved_date: date | None = None
    completion_date: date | None = None
    closing_date: date | None = None

class TaskUpdate(BaseModel):
    task_title: Optional[str]
    task_desc: Optional[str]
    karma: Optional[int]
    task_status: Optional[str]
    reservedby: Optional[str]
    rmrks: Optional[str]
