#
#  Be sure to run `pod spec lint LoggedOut.podspec' to ensure this is a
#  valid spec and to remove all comments including this before submitting the spec.
#
#  To learn more about Podspec attributes see http://docs.cocoapods.org/specification.html
#  To see working Podspecs in the CocoaPods repo see https://github.com/CocoaPods/Specs/
#

Pod::Spec.new do |s|
    s.name         = "AssociationShared"
    s.version      = "0.0.1"
    s.summary      = "Shared Kotlin Native components for Association Project."
    s.description  = <<-DESC
                        This podspec makes it possible for an iOS client project to import result of
                        Kotlin Native Multiplatform build as Pod frameworks without knowing the details
                        of how the frameworks are created.
                     DESC

    s.homepage     = "https://github.com/makegoodstuff/AssociationShared"
    s.license      = "MIT"
    s.authors      = {
        "Justin Kaufman"  => "jmkauf@gmail.com",
        "Ermolay Romanov" => "hello@ermolay.com"
    }
    s.platform     = :ios, '8.0'

    s.source       = { :git => "https://github.com/makegoodstuff/AssociationShared.git", :tag => "master" }

    # s.ios.vendored_frameworks = "build/bin/ios/**/*.framework"
    s.script_phase = {
        :name => '[Gradle] Generate iOS Frameworks from Kotlin Native code',
        # :script => '$SRCROOT/../../gradlew -p "$SRCROOT/.." "export" -PtargetDirectory="$PODS_BUILD_DIR"',
        :script => '$PODS_ROOT/AssociationShared/Resources//gradlew -p "$SRCROOT/.." "export" -PtargetDirectory="$PODS_BUILD_DIR"',
        :execution_position => :before_compile
    }

    s.subspec 'ChangePassword' do |sp|
        sp.resources = 'ChangePassword'
        sp.ios.deployment_target = '8.0'
    end
  end
