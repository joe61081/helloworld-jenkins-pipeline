pipeline {
	agent {
		node { label 'docker-slave-cluster' }
	}
	options { buildDiscarder(logRotator(numToKeepStr: '2', artifactNumToKeepStr: '2')) }
	triggers {
		githubPush()
	}

	stages {
		stage('Deploy') {
			when{
				branch 'master'
			}
			steps {
				script {
					sh "echo 'Deploying Master...'"
					sh 'mvn install'

				}

			}
		}
		stage('Develop Release') {
			when{
				branch 'release*'
			}
			steps {
				script {
					sh "echo 'Release...'"
					sh 'mvn install'

				}

			}
		}
		stage('Feature') {
			when{
				branch 'feature*'
			}
			steps {
				script {
					sh "echo 'Feature...'"
					sh 'mvn install'

				}

			}
		}
		stage('Develop') {
			when{
				tag 'dev-*'
			}
			steps {
				script {
					sh "echo 'Develop...'"
					sh 'mvn install'

				}

			}
		}
		stage('SIT') {
			when{
				tag 'sit-*'
			}
			steps {
				script {
					sh "echo 'SIT...'"
					sh 'mvn install'
				}

			}
		}
		stage('UAT') {
			when{
				tag 'uat-*'
			}
			steps {
				script {
					sh "echo 'UAT...'"
					sh 'mvn install'

				}

			}
		}
		stage('PRE') {
			when{
				tag 'pre-*'
			}
			steps {
				script {
					sh "echo 'PRE...'"
					sh 'mvn install'

				}

			}
		}
		stage('PRD') {
			when{
				tag 'prd-*'
			}
			steps {
				script {
					sh "echo 'PRD...'"
					sh 'mvn install'

				}

			}
		}
	}
}