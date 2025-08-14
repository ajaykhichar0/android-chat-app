# Android Chat App

A simple Android chat application built with Kotlin and XML layouts that demonstrates swipe-to-delete functionality.

## Features

- **Two User Chat Interface**: Displays messages from sender (You) and receiver (Friend)
- **Alternating Message Flow**: Messages alternate between sender and receiver
- **Swipe-to-Delete**: Left swipe on any message to reveal delete options
- **Delete Confirmation**: Shows delete icon (✕) and delete button before actual deletion
- **Modern UI**: Clean, modern design with rounded message bubbles
- **Responsive Layout**: Works on different screen sizes

## How to Use

1. **View Messages**: The app loads with sample conversation between two users
2. **Delete Messages**: 
   - Swipe left on any message to reveal delete options
   - Click the "✕" icon or "Delete" button to remove the message
   - Swipe right to hide delete options without deleting

## Project Structure

```
app/
├── src/main/
│   ├── java/com/example/chatapp/
│   │   ├── MainActivity.kt          # Main activity with RecyclerView setup
│   │   ├── ChatAdapter.kt           # RecyclerView adapter with swipe detection
│   │   └── Message.kt               # Data class for messages
│   ├── res/
│   │   ├── layout/
│   │   │   ├── activity_main.xml    # Main activity layout
│   │   │   ├── item_message_sender.xml    # Sender message layout
│   │   │   └── item_message_receiver.xml  # Receiver message layout
│   │   ├── values/
│   │   │   ├── colors.xml           # Color definitions
│   │   │   ├── strings.xml          # String resources
│   │   │   └── themes.xml           # App theme
│   │   └── drawable/
│   │       ├── bg_message_sender.xml     # Sender bubble background
│   │       └── bg_message_receiver.xml   # Receiver bubble background
│   └── AndroidManifest.xml
└── build.gradle
```

## Technical Implementation

### Swipe Detection
- Uses `OnTouchListener` to detect touch events
- Calculates horizontal swipe distance with threshold of 100px
- Distinguishes between left swipe (show delete) and right swipe (hide delete)
- Prevents accidental triggers by checking vertical movement

### Message Management
- `Message` data class with id, text, sender type, and swipe state
- `ChatAdapter` handles different view types for sender/receiver
- Dynamic visibility of delete options based on swipe state
- Smooth item removal with `notifyItemRemoved()`

### UI Design
- Material Design principles with custom colors
- Rounded message bubbles with different styles for sender/receiver
- Sender messages: Blue background, right-aligned
- Receiver messages: Gray background, left-aligned
- Delete options: Red colored icon and text

## Requirements

- Android SDK 24+ (Android 7.0)
- Kotlin support
- Material Design Components

## Installation

1. Open the project in Android Studio
2. Sync Gradle files
3. Run the app on an emulator or physical device

## Usage Instructions

1. Launch the app to see the chat interface
2. Scroll through the conversation
3. Try swiping left on any message to see the delete option
4. Click the delete button to remove the message
5. Swipe right to cancel delete action

The app demonstrates a clean, intuitive chat interface with gesture-based message deletion functionality.
