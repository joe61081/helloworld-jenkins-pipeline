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
				sh 'mvn install'
				sh 'mvn run'
			}

		}
		stage('feature'){
			when{
				branch "feature*"
			}
			steps{
				runmvn();
			}

		}
		stage('DEV'){
			when{
				tag "dev-*"
			}
			steps{
				runmvn();
			}
		}
		stage('SIT'){
			when{
				tag "sit-*"
			}
			steps{
				runmvn();
			}

		}
		stage('UAT'){
			when{
				tag "uat-*"
			}
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
			when{
				tag "prd-*"
			}
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
