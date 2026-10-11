# TallerJava

Proyecto de práctica con cinco ejercicios independientes y un menú para abrirlos.
Esta versión usa nombres de clases, paquetes y carpetas propios, conservando los
métodos y el comportamiento solicitado en el taller.

## Ejercicios

1. **Caja** (`tallerjava.caja.CajeroDigital`): banco, cuentas de ahorro y corriente,
   transferencias e historial de movimientos.
2. **Academia** (`tallerjava.academia.ControlCurso`): materias, asistencias,
   estadísticas, búsqueda, modificación de notas y reporte.
3. **Comercio** (`tallerjava.comercio.PuntoDeVenta`): categorías, inventario,
   subtipos de artículos, clientes y descuentos.
4. **Directorio** (`tallerjava.directorio.DirectorioPrincipal`): contactos,
   validación, grupos, búsqueda parcial y almacenamiento en archivo.
5. **Calificaciones** (`tallerjava.calificaciones.EvaluadorCurso`): notas ponderadas,
   curva porcentual, recuperación y estadísticas.

El menú principal es `tallerjava.inicio.MenuPrincipal`; permite elegir del 1 al 5 y
usa 0 para salir.

## Compilar y ejecutar el menú

Se necesita un JDK. En PowerShell, abre esta carpeta y ejecuta:

```powershell
$fuentes = Get-ChildItem Ejercicio*/src, MenuPrincipal/src -Recurse -Filter *.java
javac -encoding UTF-8 -d salida $fuentes.FullName
java -cp salida tallerjava.inicio.MenuPrincipal
```

Cada ejercicio también se puede ejecutar por separado usando su clase principal.
La agenda guarda los contactos en `agenda.txt` en la carpeta desde la que se ejecuta.
