package crosscutting.messages;

public enum ErrorMessage {
    PREPARING_QUERY("Se ha presentado un error inesperado llevando acabo la operación deseada.", "Se ha presentado un error inesperado preparando la consulta, para mayor información revisar el Log de errores"),
    EXECUTING_QUERY("Se ha presentado un error inesperado llevando acabo la operación deseada.", "Se ha presentado un error inesperado ejecutando la consulta, para mayor información revisar el Log de errores");

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
