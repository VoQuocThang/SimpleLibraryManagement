CREATE DATABASE LibraryDatabase;


Use LibraryDatabase;


insert into Admin (ID,name,password)
       values ('1','thang','123');

select * 
from Admin;

select * 
from Librian

select *
from Book

select * 
from Issued


update Book
set quantity = quantity-1,issued=0;

