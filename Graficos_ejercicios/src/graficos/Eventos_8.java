/* TEORÍA CLASE ANÓNIMA
 
 
 
Una clase anónima en Java es una clase interna sin nombre que se define y se instancia en una sola expresión, generalmente en el lugar donde se necesita utilizarla y solo para un uso puntual[1][4][5]. Se utilizan principalmente para extender una clase existente o implementar una interfaz, especialmente cuando solo se requiere una implementación específica y no se va a reutilizar ese código en otra parte[1][2][5][10].

Características principales
- No tiene nombre propio ni archivo separado: Se declara e instancia en el mismo lugar del código donde se necesita.
- No se puede reutilizar: Solo se puede usar en ese punto del código y no se pueden crear nuevas instancias de esa clase anónima después.
- Acceso a variables del contexto**: Puede acceder a variables y miembros de la clase donde está definida, y también a variables locales que sean *finales* o *efectivamente finales* (es decir, que no cambian después de ser inicializadas).
- Sintaxis compacta: Permite escribir código más conciso y claro cuando solo necesitas una implementación rápida, por ejemplo, para manejar eventos en interfaces gráficas.

Para implementar una interfaz: */

boton.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent e) {
        System.out.println("¡Botón pulsado!");
    }
});

/*

¿Para qué se usan?

- Son muy útiles en el manejo de eventos (por ejemplo, en interfaces gráficas con botones o casillas de verificación).
- Evitan tener que crear clases completas solo para un uso puntual, haciendo el código más limpio y fácil de leer.

Resumen

> Una clase anónima en Java es una clase interna sin nombre que se declara y se instancia en una sola expresión, normalmente para implementar una interfaz o extender una clase de manera rápida y puntual, especialmente útil en el manejo de eventos o callbacks.

