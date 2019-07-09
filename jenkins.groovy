pipeline {
	agent {
		node { label 'docker-slave-cluster' }
	}
	options { buildDiscarder(logRotator(numToKeepStr: '2', artifactNumToKeepStr: '2')) }
	triggers {
		githubPush()
	}

	stages {
		stage('Setup'){
			steps{
				checkout([$class: 'GitSCM', branches: [[name: '*/master'], [name: '*/develop'], [name: 'feature*'], [name: 'release*']], doGenerateSubmoduleConfigurations: false, extensions: [], submoduleCfg: [], userRemoteConfigs: [[credentialsId: 'joe-github-ssh-token', url: 'git@github.com:joe61081/helloworld-jenkins-pipeline.git']]])
			}
		}
		stage('Deploy') {
			when{
				expression {env.GIT_BRANCH == 'origin/master'}
			}
			steps {
				script {
					sh "echo 'Deploying Master...'"
					sh 'mvn install'
					sh 'printenv'

				}

			}
		}
		stage('Release') {
			when{
				expression {env.GIT_BRANCH == 'origin/develop'}
			}
			steps {
				script {
					sh "echo 'Release...'"
					sh 'mvn install'
					sh 'mvn verify'

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
					sh 'printenv'

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
					sh 'mvn verify'

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
					sh 'mvn verify'
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
					sh 'mvn verify'

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
					sh 'mvn verify'

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
					sh 'mvn verify'

				}

			}
		}
	}
}