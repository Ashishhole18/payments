stage('SCM-Clonning') {
    git 'https://github.com/Ashishhole18/payments'
}

stage('Package-Application') {
    def mavenHome = tool name: 'apache', type: 'maven'
    sh "${mavenHome}/bin/mvn package"
 }

stage('Slack-Notification') {
    slackSend baseUrl: 'https://hooks.slack.com/services/',
    channel: '#payments-offocial-jenkins-job',
    color: 'good',
    message: 'Hello Build has ended',
    tokenCredentialId: 'slack-demo'
}

stage('Email-Notification') {
    mail bcc: '',
    body: 'This is testing build complete messages',
    cc: 'ashish.holey56@gmail.com',
    from: '', replyTo: '',
    subject: 'Build has been completed',
    to: 'newinvotech@gmail.com'
}