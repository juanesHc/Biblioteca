package crosscutting.exceptions.enums;

public enum ErrorMessagesSQL {
    ROLE_QUERY_ERROR(
            "Se ha presentado un problema llevando a cabo la consulta de roles por filtro, por favor intente de nuevo",
            "Se ha presentado un problema llevando a cabo la consulta de roles por filtro en PosgreSQL, por favor revise el log de errores"
    );

    private final String userMessage;
    private final String technicalMessage;

    // Constructor
    ErrorMessagesSQL(String userMessage, String technicalMessage) {
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
