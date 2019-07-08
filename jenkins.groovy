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
					sh "echo 'Deploying...'"
					sh 'printenv'
					sh 'mvn install'
					sh 'mvn verify'

				}

			}
		}
	}
}