job('MiAplicacionNodeJS') {
    description('aplicación Node.js usando DSL')
    
    scm {
        git('https://github.com/<tu-usuario>/<tu-repositorio>.git', 'main') { node ->
            node / gitConfigName('<tu-nombre>')
            node / gitConfigEmail('<tu-correo>')
        }
    }
    
    triggers {
        scm('H/5 * * * *') // Revisa cambios cada 5 minutos
    }
    
    wrappers {
        nodejs('nodejs') // Requiere que tengas configurado el entorno Node.js en Jenkins
    }
    
    steps {
        shell("npm install") // Instala dependencias
        shell("npm start")   // Inicia la aplicación
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
