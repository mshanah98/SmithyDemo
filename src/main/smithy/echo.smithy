$version: "2"

namespace demo

use aws.api#service
use aws.protocols#restJson1

@service(sdkId: "Demo")
@restJson1
service DemoService {
    operations: [
        Echo
    ]
}

@http(method: "POST", uri: "/echo")
operation Echo {
    input: EchoInput
    output: EchoOutput
}

structure EchoInput {
    @required
    message: String
}

structure EchoOutput {
    @required
    message: String
}
