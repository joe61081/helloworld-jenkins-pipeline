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
				expression {env.GIT_BRANCH == 'origin/master'}
			}
			steps {
				script {
					sh "echo 'Deploying Master...'"
					sh 'printenv.GIT_BRANCH'
					sh 'mvn install'
					sh 'mvn verify'

				}

			}
		}
		stage('Release') {
			when{
				expression {env.GIT_BRANCH == 'origin/release*'}
			}
			steps {
				script {
					sh "echo 'Release...'"
					sh 'printenv.GIT_BRANCH'
					sh 'mvn install'
					sh 'mvn verify'

				}

			}
		}
		stage('Feature') {
			when{
				expression {env.GIT_BRANCH == 'origin/feature*'}
			}
			steps {
				script {
					sh "echo 'Feature...'"
					sh 'printenv.GIT_BRANCH'
					sh 'mvn install'
					sh 'mvn verify'

				}

			}
		}
	}
}