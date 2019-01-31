-- SQL(DML) Escribir la sentencia sql que permita obtener la trazabilidad
-- de una tarea incluida la informaciòn de los responsables.
select nombre, descripcion, fechaCreacion, estado,nombreresponsable
  from tarea t inner join responsable r
	on t.responsable_idresponsable=r.idresponsable;
