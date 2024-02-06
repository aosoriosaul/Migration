node {
    properties([
        parameters([
            text(name: 'projectId', defaultValue: '34', description: 'Identificador de proyecto en gitlab'),
            booleanParam(defaultValue: true, description: 'LimpiarWorkspace', name: 'LimpiarWorkspace'),
            booleanParam(name: 'keepNode', defaultValue: false, description: 'Conserva la carpeta node'),
            booleanParam(name: 'sonarScanning', defaultValue: false, description: 'Analisis de codigo Sonar'),
            booleanParam(name: 'probar', defaultValue: false, description: 'Api proxy testing'),
            booleanParam(name: 'mocks', defaultValue: false, description: 'mocks api proxy')
        ])
    ])

    deleteDir()
    
    withCredentials([sshUserPrivateKey(credentialsId: 'integracioncontinua-ssh', keyFileVariable: 'KEY', passphraseVariable: 'PASS', usernameVariable: 'USER')]) {
        sh '/usr/bin/git clone https://35.209.71.235/cicd/jenkins/pipeline/jenkinsfile-apiproxy.git'
    }

    MOCKS_BASEPATH = "/italika/transformacion-digital/gestion-motos/v1.1.0"
    def rootDirectory=pwd()
    def jenkinsfile =  load "${rootDirectory}/jenkinsfile-apiproxy/jenkinsfilemaster.groovy"
    jenkinsfile.mainFlow()
}
