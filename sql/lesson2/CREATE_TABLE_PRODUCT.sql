CREATE TABLE PRODUCT (
ID NUMBER PRIMARY KEY,
NAME NVARCHAR2(128) NOT NULL,
DESCRIPTION CLOB,
PRICE INTEGER NOT NULL
);

CREATE TABLE ORDERS (
ID NUMBER PRIMARY KEY,
NAME NVARCHAR2(128) NOT NULL,
PRICE INTEGER,
DATEORDERED DATE,
DATECONFIRMED DATE
);

INSERT INTO ORDERS VALUES
(5001, 'PRODUCT1', 100, TO_DATE('2017/11/30 21:55:59', 'yyyy/mm/dd hh24:mi:ss'),
TO_DATE('2017/11/30 21:55:59', 'yyyy/mm/dd hh24:mi:ss'));
INSERT INTO ORDERS VALUES
(5002, 'PRODUCT2', 200, TO_DATE('2017/12/30 21:55:59', 'yyyy/mm/dd hh24:mi:ss'),
TO_DATE('2017/12/30 21:55:59', 'yyyy/mm/dd hh24:mi:ss'));
INSERT INTO ORDERS VALUES
(5003, 'PRODUCT3', 300, TO_DATE('2017/10/30 21:55:59', 'yyyy/mm/dd hh24:mi:ss'),
TO_DATE('2017/10/30 21:55:59', 'yyyy/mm/dd hh24:mi:ss'));


SELECT * FROM ORDERS;