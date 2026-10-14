// swift-tools-version: 5.9
import PackageDescription
let package = Package(
  name: "dev_gitlive_firebase_storage_3_0_0_alpha02",
  platforms: [
    .iOS("16.0")
  ],
  products: [
    .library(
      name: "dev_gitlive_firebase_storage_3_0_0_alpha02",
      type: .none,
      targets: ["dev_gitlive_firebase_storage_3_0_0_alpha02"]
    )
  ],
  dependencies: [
    .package(
      url: "https://github.com/firebase/firebase-ios-sdk.git",
      from: "12.17.0"
    )
  ],
  targets: [
    .target(
      name: "dev_gitlive_firebase_storage_3_0_0_alpha02",
      dependencies: [
        .product(
          name: "FirebaseStorage",
          package: "firebase-ios-sdk"
        )
      ]
    )
  ]
)
