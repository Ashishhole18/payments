node{
    stage('SCM Checkout'){
    git 'https://github.com/Ashishhole18/payments'
    }
    stage('Package-Application') {
        def mavenHome = tool name: 'apache', type: 'maven'
        sh "${mavenHome}/bin/mvn package"

    }

}