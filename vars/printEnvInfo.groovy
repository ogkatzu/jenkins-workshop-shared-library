// vars/printEnvInfo.groovy

def call(config = [:]){
    echo 'branch name ${env.BRANCH_NAME}'
    echo 'build number ${env.BUILD_NUMBER}'
    echo 'Jenkins URL ${env.JENKINS_URL}'
}
