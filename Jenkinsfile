node{
    stage('SCM Checkout'){
    git 'https://github.com/Ashishhole18/payments'


    }
    stage('Compile-Package') {
    def mvnHome = tool name: 'apache', type: 'maven'

    sh "${mvnHome}/bin/mvn package"
    }
    stage('Email notification') {
    mail bcc: '', body: '''This is testing jenkin Job alert
    Thanks
    Payments''', cc: '', from: '', replyTo: '', subject: 'Jenkin Job', to: 'ashish.holey56@gmail.com'

    }
}