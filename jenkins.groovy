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
		stage('Release') {
			when{
				branch 'develop'
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
				expression {env.GIT_BRANCH == 'origin/dev-*'}
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
				expression {env.GIT_BRANCH == 'origin/sit-*'}
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
				expression {env.GIT_BRANCH == 'origin/uat-*'}
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
				expression {env.GIT_BRANCH == 'origin/pre-*'}
			}
			steps {
				script {
					sh "echo 'Feature...'"
					sh 'mvn install'

				}

			}
		}
		stage('PRD') {
			when{
				expression {env.GIT_BRANCH == 'origin/prd-*'}
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