 create table department (
      dept_id integer not null,
      dept_name varchar(30) not null,
      dept_location varchar(30) not null,
      unique(dept_id)
  );

  create table employee (
      emp_id integer not null,
      emp_name varchar(50) not null,
      dept_id integer not null,
      salary integer not null,
      unique(emp_id)
  );