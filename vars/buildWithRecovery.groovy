// vars/buildWithRecovery.groovy

def call(Map config = [:]) {
    def environment = config.environment ?: 'dev'

    try {
        echo "Running maven build for env=${environment}"
        sh "mvn install"
    } catch (Exception e) {
        echo "mvn install failed with ${e.message}, trying clean install"
        try {
            sh "mvn clean install -U"
            echo "Build successful on retry"
        } catch (Exception retryerror) {
            echo "retry also failed"
            throw retryerror
        }
    }
    finally {
        echo "cleaning up"
        sh "rm -rf target/tmp || true"
    }
}