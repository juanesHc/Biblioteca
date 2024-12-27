package crosscutting.messages;

public enum ErrorMessage {
    PREPARING_QUERY("Se ha presentado un error inesperado llevando acabo la operación deseada.", "Se ha presentado un error inesperado preparando la consulta, para mayor información revisar el Log de errores"),
    EXECUTING_QUERY("Se ha presentado un error inesperado llevando acabo la operación deseada.", "Se ha presentado un error inesperado ejecutando la consulta, para mayor información revisar el Log de errores"),
    CONNECTION("Se ha presentado un error inesperado","Se ha presentado un error inesperado al llevar a cabo la conexión con la base de datos, para mayor información revisar el Log de errores"),
    CONNECTION_ALREADY_OPEN("Se ha presentado un error inesperado","No es posible abrir una conexión que ya esta abierta"),
    CONNECTION_VALIDATION("Se ha presentado un problema inesperado intentando llevar acabo la operación deseada","Se ha presentado una excepción de tipo SQLException llevando acabo la validacion de sí la conexión estaba abierta , para más detalles revise el log de errores"),
    CONNECTION_CLOSE("Se ha presentado un problema inesperado intentando llevar acabo la operación deseada","La conexión hacía la base de datos está cerrada por ende no es posible tratar de abrir la conexión , para más detalles revise el log de errores"),
    INIT_TRANSACTION("Se ha presentado un problema inesperado intentando iniciar la transacción","Se ha presentado una excepción de tipo SQLException iniciando la transacción, para más detalles revise el log de errores"),
    TRANSACTION_ALREADY_INITIALIZED("Se ha presentado un problema inesperado intentando iniciar la transacción","No es posible inicializar una transacción que ya ha sido inicializada"),
    CONFIRM_TRANSACTION("Se ha presentado un problema inesperado intentando confirmar la transacción","No es posible confirmar una transacción que ya ha sido inicializada"),
    ROLLBACK_TRANSACTION("Se ha presentado un problema inesperado intenando cancelar la transacción","No es posible cancelar una transacción que ya ha sido inicializada"),
    VALIDATE_TRANSACTION("Se ha presentado un problema inesperado intenando validar la transacción","Se ha presentado una excepcion de tipo SQLException intentando validar la transacción para más información revisar el log de errores"),
    WAS_NOT_INITIALIZED("Se ha presentado un problema inesperado intenando validar la transacción","La transacción no ha sido inicializada previamente"),
    CLOSE_TRANSACTION("Se ha presentado un problema inesperado intenando cerrar la conexión","Se ha presentado una excepción de tipo SQLException cerrando la transacción, para más detalles revise el log de errores"),
    OPEN_TRANSACTION("Se ha presentado un problema inesperado intenando validar la transacción","Se ha presentado una excepción de tipo SQLException abriendo la transacción, para más detalles revise el log de errores");

    private final String userMessage;
    private final String technicalMessage;

    // Constructor
    ErrorMessage(String userMessage, String technicalMessage) {
        this.userMessage = userMessage;
        this.technicalMessage = technicalMessage;
    }

    // Métodos para obtener los mensajes
    public String getUserMessage() {
        return userMessage;
    }

    public String getTechnicalMessage() {
        return technicalMessage;
    }
}
