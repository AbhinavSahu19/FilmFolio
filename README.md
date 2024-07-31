Overview
FimlFolio is an Android application that allows users to discover and manage movies and TV shows.
Utilizing the TMDB API, this app fetches and displays detailed information about movies and shows, providing a rich and interactive experience. 
Users can create custom lists, search for content, and manage their preferences with ease.

Features
Trending Movies & Shows: Stay updated with the latest trending content.
Genre Browsing: Explore movies and TV shows by genre.
Detailed Information: View comprehensive details about movies and shows, including cast and similar titles.
Personalized Lists: Create and manage custom lists such as "To Watch" and "Favorites".
Search Functionality: Search for movies, series, and people with keyword recommendations.
Firebase Authentication: Sign in with Google for personalized experience.
Search History Management: Keep track of your searches and remove keywords as needed.
User Account Management: Sign in, sign out, and delete your account.

Technologies Used
Kotlin: The primary programming language for Android development.
Jetpack Compose: Modern toolkit for building native UI.
MVVM Architecture: Ensures a clear separation between UI, ViewModel, and Repository.
Navigation: Handles in-app navigation.
Dagger Hilt: Dependency injection framework.
Retrofit: For network API calls to fetch data from TMDB.
Firebase Authentication: Manages user authentication.
Firebase Realtime Database: Stores user data and lists.
Kotlin Flow: Handles data streams and reactive programming.
Kotlin Coroutines: Manages asynchronous operations.
Project Structure
The project follows the MVVM architecture pattern, ensuring a clean separation of concerns:

UI Layer: Built using Jetpack Compose.
ViewModel Layer: Manages UI-related data.
Repository Layer: Handles data operations, integrating with Retrofit for network calls and Firebase for database operations.
Navigation: Manages in-app navigation.
Dependency Injection: Configured using Dagger Hilt.
