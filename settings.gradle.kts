rootProject.name = "pancake"

include("application")

include("libraries:config")
include("libraries:event")
include("libraries:logging")
include("libraries:security")
include("libraries:utils")
include("libraries:utils-jvm")
// include("api-mime")
// include("api-smtp")

include("pancake:core")
include("pancake:database")

// include("plugin:base")
// include("plugin:cli")
// include("plugin:http")
// include("plugin:smtp")
