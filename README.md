# Getir Android Kotlin Bootcamp Final Project


https://github.com/blaugranaa/getir-android-kotlin-bootcamp-FinalProject-AysenurYavuz/assets/24704582/d0f5c1f8-4d4c-434f-ab61-6c7a9d137a1f


## Description

This is an e-commerce mobile application developed for Android. Users can browse products, view details, add items to the cart, and place orders.

## Technologies Used

- MVVM Architecture
- Android Jetpack Components (ViewModel, LiveData, Navigation Component)
- Glide Library

## Project Structure

- **Model**: Data classes and data management logic.
- **View**: User interface components (XML files and Fragments).
- **ViewModel**: Acts as a mediator between View and Model layers.

The main components of the application are as follows:

- **HomeFragment**: Represents the main screen where users can list and add products to their carts.
- **ProductPageFragment**: Displays the details of a product. Users can add the product to their cart or remove it from the cart here.
- **CartPageFragment**: A page where users can view their cart. It lists the products in the cart, and users can complete the order.
- **GlobalDataHolder**: A singleton object that holds data used throughout the application. Cart information and product lists are stored here.
- **ShoppingCart**: Class responsible for cart operations. Operations such as adding, removing products, and calculating the total price are performed here.
- **DataRepository**: Class used for fetching data from the server. Retrofit is used to send requests to the server, and the received data is processed and passed to the ViewModel.
- **ShoppingCartAdapter**: Adapter used to list the cart in a RecyclerView. It enables the listing of products in the cart.


## Installation

1. Clone this repository.
2. Open the project in Android Studio.
3. Run the project on an emulator or physical device.

## Contributions

Contributions, suggestions, and feedback are welcome! Please feel free to submit pull requests or open issues.

## License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.


