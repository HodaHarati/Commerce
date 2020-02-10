# Commerce

![splashPNG](https://user-images.githubusercontent.com/54942448/74115947-a6aa2e00-4bc6-11ea-9854-18bc90be6212.PNG)
![allProduct](https://user-images.githubusercontent.com/54942448/74114265-4ca56a80-4bbe-11ea-952a-d97bca0b9899.PNG)
![detail](https://user-images.githubusercontent.com/54942448/74114520-d1dd4f00-4bbf-11ea-92a3-a43fac192259.PNG)
This is an online shop. In this app I work whit REST full API. Products are on a Woocomerce website. I've used Retrofit2 library to fetch items from net and Gson library is used to get the model.

At first you see splash screen that Lottie animation is used for this, and after some seconds you see the main page. 

In this page you see 3 lists of products contains: newest products, most visited and popular products.(The Picasso library is used to display photos of product.)
by click on each product, new page will apear that has image of product on slider and also name, description, orginal and sale price. 
This page also has add_to_cart botton. By click on it new page will be apear. You can see all of product that you add them to cart. For this I've used Realm database.

![allproduct](https://user-images.githubusercontent.com/54942448/74114626-6d6ebf80-4bc0-11ea-9afb-a424f16a294b.PNG)
![subcategory](https://user-images.githubusercontent.com/54942448/74114681-b45cb500-4bc0-11ea-8a06-48cb91c659c5.PNG)

In the main page if you want to see all of product's list you have to click on the text view at the left side of list. 
Also in the main page you could see some bottons under the slider that show all categories. 
By click on each of them you see new page that have view pager with tab layout to show all categories and subcategories. 
When you click on subcategory you can see all of products of it. 
In the main page also search view is used to search in all product.

![Navigation](https://user-images.githubusercontent.com/54942448/74116327-5502a300-4bc8-11ea-98b4-c428915b299b.PNG)
![login](https://user-images.githubusercontent.com/54942448/74116360-76fc2580-4bc8-11ea-9a10-7e831e56e178.PNG)
![networkcheck](https://user-images.githubusercontent.com/54942448/74116382-8ed3a980-4bc8-11ea-97bb-4fee042ac0ca.PNG)
In the main page by click on the navigation view you could access any section you want. Also you could login or sign up.
This application works when you are connected to the network. If you aren't connected, the app will alert you by a new page. 
In this page ther is a botton, if you are connected again you could press the botton and Keep up the work.(for this I've used broadcast reciver)

In summary:
The architecture used is MVVM, I've used Live Data and data binding. I've used Repository and it is singletone. 




