job('MiAplicacionNodeJSDocker') {
    description('Pipeline para mi aplicación Node.js con Docker usando DSL')
    
    scm {
        git('https://github.com/luimungar3/nodejs_app', 'main') { node ->
            node / gitConfigName('Miguel')
            node / gitConfigEmail('correojenkins09@gmail.com')
        }
    }
    
    triggers {
        scm('H/5 * * * *') // Revisa cambios cada 5 minutos
    }
    
    wrappers {
        nodejs('nodejs') // Requiere que tengas configurado el entorno Node.js en Jenkins
    }
    
    steps {
        shell("docker build -t mi-nodejs-app .")  // Construye la imagen Docker
        shell("docker run -p 3000:3000 mi-nodejs-app") // Ejecuta la imagen Docker
    }
    
    publishers {
        slackNotifier {
            notifyAborted(true)
            notifyEveryFailure(true)
            notifyNotBuilt(false)
            notifyUnstable(false)
            notifyBackToNormal(true)
            notifySuccess(true)
            notifyRepeatedFailure(false)
            startNotification(false)
            includeTestSummary(false)
            includeCustomMessage(false)
            customMessage(null)
            sendAs(null)
            commitInfoChoice('NONE')
            teamDomain(null)
            authToken(null)
        }
    }
}
