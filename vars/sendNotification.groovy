// vars/sendNotification.groovy

def call(Map config = [:]) {
    def channel = config.channel ?: 'C097AHHS8TE'
    def message = config.message
    def color = config.color ?: 'good'
    
    if (!message) {
        error "Message is required for sendNotification"
    }

    echo "Sending message to ${channel} channel with ${color} color"
    echo "The message is: ${message}"

    slackSend(
        channel: channel,
        color: color,
        message: message
    )
}