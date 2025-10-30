// vars/healthCheck.groovy

def call(Map config = [:]) {
    def maxRetries = config.maxRetries ?: 3
    def delayTime = config.delayTime ?: 5
    def url = config.url

    def success = false
    def attampts = 0
    def responseCode = 0

    while( attampts < maxRetries && !success) {
        attampts++
        try {
            responseCode = sh(
                script: "curl -o /dev/null -s -w '%{http_code}' ${url}",
                returnStdout: true
            ).trim()
            if(responseCode == '200') {
                echo "Health check passed"
                return true
            } else {
                echo "Received non-200 response: ${responseCode}"
            }
        }
        catch (Exception e) {
            echo "health check fails with ${e.message}"
        }
        if (attampts < maxRetries) {
                echo "retrying in ${delayTime}"
                sleep delayTime    
        }
    }
    echo "Health check failed after ${maxRetries} attempts"
}