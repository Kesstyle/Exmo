stages {
    stage("Preparation") {
        node {
            checkout scm
            gradleHome = tool "Gradle 4"
        }
    }

    stage("Build") {
        node {
            bat("${gradleHome}/bin/gradle clean :exmo-api:jar :coinmarketcap-api:jar")
            archiveArtifacts artifacts: '**/build/libs/*-api*.jar'
        }
    }

    stage("Publish") {
        node {
            bat ("${gradleHome}/bin/gradle publish")
        }
    }
}


post {
    always {
          step([$class: 'Mailer',
            notifyEveryUnstableBuild: true,
            recipients: "kess@tut.by kess2007@mail.ru",
            sendToIndividuals: true])
    }
  }
}