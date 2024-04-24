# Getir Android Kotlin Bootcamp Final Project

## Project Description

This project has been developed for the final assignment of the Getir Android Kotlin Bootcamp. It aims to create a shopping application using Android app development skills. Users can list, add to cart, and order products through this application.

## Project Structure

The main components of the application are as follows:

- **HomeFragment**: Represents the main screen where users can list and add products to their carts.
- **ProductPageFragment**: Displays the details of a product. Users can add the product to their cart or remove it from the cart here.
- **CartPageFragment**: A page where users can view their cart. It lists the products in the cart, and users can complete the order.
- **GlobalDataHolder**: A singleton object that holds data used throughout the application. Cart information and product lists are stored here.
- **ShoppingCart**: Class responsible for cart operations. Operations such as adding, removing products, and calculating the total price are performed here.
- **DataRepository**: Class used for fetching data from the server. Retrofit is used to send requests to the server, and the received data is processed and passed to the ViewModel.
- **ShoppingCartAdapter**: Adapter used to list the cart in a RecyclerView. It enables the listing of products in the cart.

## Technologies and Libraries

The main technologies and libraries used in this project are:

- **Kotlin**: The primary programming language used for developing the application.
- **Android Jetpack Libraries**: Architecture components such as ViewModel, LiveData are used to build the architecture.
- **Glide**: Library used for loading and displaying images.
- **Retrofit**: Library used for HTTP requests.

## Installation and Usage

To run the project locally, you can follow these steps:

1. Open Android Studio.
2. Clone or download the project from the [GitHub repository](h[ttps://github.com/user/repository](https://github.com/blaugranaa/getir-android-kotlin-bootcamp-FinalProject-AysenurYavuz/tree/main)).
3. Open the project in Android Studio.
4. Run the project by selecting an emulator or a physical device.

