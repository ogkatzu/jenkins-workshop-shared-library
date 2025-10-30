// vars/sendSlackMessage.groovy

def call(config = [:]) {
    def colorMap = [
        'STARTED': 'warning',
        'SUCCESS': 'good',
        'FAILURE': 'danger'
    ]
    def color = colorMap[buildStatus] ?: 'warning'
    def message = "${buildStatus}: Job ${env.JOB_NAME} [${env.BUILD_NUMBER}]"

    slackSend color: color, message: message
}