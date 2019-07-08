pipeline {
	agent {
		node { label 'docker-slave-cluster' }
	}
	options { buildDiscarder(logRotator(numToKeepStr: '2', artifactNumToKeepStr: '2')) }

	stages {
		stage('Deploy') {
			steps {
				script {
					sh "echo 'Hello World'"
					sh 'mvn install'
					sh 'mvn verify'

				}

			}
		}
	}
}