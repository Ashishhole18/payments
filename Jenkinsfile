node{
    stage('SCM Checkout'){
    git 'https://github.com/Ashishhole18/payments'


    }
    stage('Compile-Package') {
    def mvnHome = tool name: 'apache', type: 'maven'

    sh "${mvnHome}/bin/mvn/package"
    
    }



}