-- 5 SQL (DML) Escribir la sentencia SQL que permita obtener el numero de tareas de cada responsable.

select nombreresponsable, count(idtarea) as numeroTareas
 from tarea t inner join responsable r
 on t.responsable_idresponsable=r.idresponsable
 group by idresponsable;