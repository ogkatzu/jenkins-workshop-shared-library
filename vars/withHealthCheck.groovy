// vars/withHealthCheck.groovy

def call(Map config = [:], Closure body) {
    def url = config.url
    def delayTime = 5
    def maxRetries = 3
    if (!url) {
        error "Must supply URL"
    }

    body()

    healthCheck(
        url: url, 
        maxRetries: maxRetries,
        delayTime: delayTime
    )

}