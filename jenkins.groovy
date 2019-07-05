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
	sh 'mvn install'
	sh 'mvn run'
}
