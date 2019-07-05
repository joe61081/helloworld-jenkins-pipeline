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
					git{
						remote{
							github('git@github.com:joe61081/helloworld-jenkins-pipeline.git')
							credentials('joe-github-ssh-token')
						}
					}
				}
				runmvn();
			}

		}
		stage('DEV'){
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
	sh 'mvn run'
}
