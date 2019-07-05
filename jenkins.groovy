pipeline{
	options {
		buildDiscarder(logRotator(numToKeepStr: '2', artifactNumToKeepStr: '2'))
	}
	triggers {
        githubPush()
    }
	agent any
	stages{
		stage('master'){
			steps{
				scm {
					git(url: 'git@github.com:joe61081/helloworld-jenkins-pipeline.git',credentialsId:'joe-github-ssh-token')
    			}
				sh 'mvn --version'
				runmvn();
			}

		}
		stage('feature'){
			steps{
				runmvn();
			}

		}
		stage('DEV'){
			steps{
				runmvn();
			}
		}
		stage('SIT'){
			steps{
				runmvn();
			}

		}
		stage('UAT'){
			steps{
				runmvn();
			}

		}
		stage('PRE'){
			when{
				tag "pre-*"
			}
			steps{
				runmvn();
			}

		}
		stage('PRD'){
			steps{
				runmvn();
			}

		}

	}
}

void runmvn(){
	readMavenPom file: 'pom.xml'
	sh 'mvn install'
	sh 'mvn run'
}
