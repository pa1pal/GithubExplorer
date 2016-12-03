# GithubExplorer
GitHub Explorer is an android app to search any user on GitHub and view their repositories.
The complete source code developed following Model View Presenter (MVP) architecture and some sharp libraries.

##Screenshots
<table>
  <tr>
    <td><img src="https://raw.githubusercontent.com/pa1pal/GithubExplorer/master/screenshots/search_user.png"></td>
    <td><img src="https://raw.githubusercontent.com/pa1pal/GithubExplorer/master/screenshots/search_results.png"></td>
    <td><img src="https://raw.githubusercontent.com/pa1pal/GithubExplorer/master/screenshots/users_repositories.png"></td>
  </tr>
</table>

#Libraries used in project
* [Retrofit](https://square.github.io/retrofit/)
It lets you treat API calls as simple Java method calls, so you only define which URLs to hit and the types of the request/response parameters as Java classes. It is beneficial that Picasso is not included in Retrofit because it will overburden the code library.

* [RxJava](https://github.com/ReactiveX/RxJava), [RxAndroid](https://github.com/ReactiveX/RxAndroid)
Makes the code more robust. Helps in filtering the content very easily. If work is to just load some content from JSON then Rx not prefer but when we scale the app and we have to load data from multiple sources then the callback variant will become a bit more difficult and lengthy. Process everything in the background and show the result on the main thread by emitting data one by one.

* [Picasso](http://square.github.io/picasso/)
A very lightweight image loading library.

* [Retrofit2 Adapter-rxjava](https://github.com/JakeWharton/retrofit2-rxjava2-adapter)
A RxJava CallAdapter.Factory implementation for Retrofit 2. So we can use rxjava in Retrofit.

* [DBFlow](https://github.com/Raizlabs/DBFlow)
This is an Object-Relational mapping (ORM) which is very fast and easy to use. Relies on annotation processing to generate Java code. This approach saves us from writing a lot of boilerplate code normally needed to declare your tables, manage their schema changes, and perform queries. **Please Disable Instant Run** because DBFlow relies on generating code for your table files, Android's Instant Run feature can often interfere with creating the correct set of tables.

* [Stetho](https://facebook.github.io/stetho/)
A Great library by facebook to inspect the SQLite data stored in the device.

* [Butterknife](https://github.com/JakeWharton/butterknife)
Very helpful. Omit the use of findViewById, OnItemClickListner etc and annotations are cool

* CardView and DesignSupport
For material design
