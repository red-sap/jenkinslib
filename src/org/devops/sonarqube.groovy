package org.devops
//scan
def SonarScan(projectName,projectDescription,projectPath){
    def sonarServer = "http://10.1.3.2:8900/"
    def sonarDate = sh returnStdout: true, script: 'date +%Y%m%d%H%M%S'
    sonarDate = sonarDate - "\n"

    sh  """
        sonar-scanner -Dsonar.host.url="${sonarServer}"\
        -Dsonar.projectKey=${projectName} \
        -Dsonar.projectName=${projectName} \
        -Dsonar.projectVersion=${sonarDate} \
        -Dsonar.login=admin \
        -Dsonar.password=P@ssw0rd \
        -Dsonar.ws.timeout=30 \
        -Dsonar.projectDescription=${projectDescription} \
        -Dsonar.links.homepage="" \
        -Dsonar.sources=${projectPath} \
        -Dsonar.sourceEncoding=UTF-8 \
        -Dsonar.java.binaries=target/classes \
        -Dsonar.java.test.binaries=target/test-classes \
        -Dsonar.java.surefire.report=target/surefire-reports
    """

}