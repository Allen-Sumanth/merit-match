from sqlalchemy import Column, Integer, String, DATE
from dbconfig import Base

class Tasks(Base):
    __tablename__ = 'tasks'

    taskid = Column(Integer, index=True, primary_key=True, autoincrement=True)
    username = Column(String(50))
    task_title = Column(String(150))
    task_desc = Column(String(1000))
    karma = Column(Integer)
    task_status = Column(String(25))
    assign_date = Column(DATE, default=None)
    reserved_date = Column(DATE, default=None)
    completion_date = Column(DATE, default=None)
    closing_date = Column(DATE, default=None)
    reservedby = Column(String(20), default=None)
    rmrks = Column(String(100), default=None)

class Users(Base):
    __tablename__ = 'users'

    userid = Column(Integer, primary_key=True, index=True, autoincrement=True)
    username = Column(String(50), unique=True)
    password = Column(String(150))
    karma = Column(Integer)