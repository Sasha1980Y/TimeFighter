
/*
interface  Repository<T> {
    fun addItem(item: T)
    fun deleteItem(item: T)
}

class Person(val name: String) {

}

class PersonRepository: Repository<Person> {


    override fun addItem(item: Person) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
    override fun deleteItem(item: Person) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}

fun <T> printItem(item: T) {
    println("My Item is $item")
}
fun <MyItem> printItem2(item: MyItem) {
    printItem(item)
}

fun <T> printList(list: List<T>) {
    for (item in list) {
        println("List item : $item")
    }
}

fun main(args: Array<String>) {
    printItem("Test")

    printItem(1)
    printItem("Hello")

    val list = listOf(1,2,3,4)
    printList(list)


    // LAMBDA

    println(list.filter { it > 2 })

    val names = listOf("Sam", "Fred", "Samuel", "Alexandr")
    println(names.first() {it.length > 5})

    val cities = listOf("Kyiv", "Kamenec", "Chmelnitsky")
    println(cities.any { it == "Kyiv" })// якщо хоть 1 = "Kyiv"
    println(cities.all { it.length > 3}) // кожний елемент має довжину більше 3 - true

}
*/
/*
class Person(var firstName: String) {
    var lastName: String? = null
    constructor(firstName: String, lastName: String): this(firstName) {  // init
        this.lastName = lastName
    }
}

fun main(args: Array<String>) {
    val person = Person("Sam", "Gamgee")
    val person2 = Person("Sasha")
    println(person.firstName)
    println(person2.lastName)
}
*/

/* // CLASSES
fun main(args: Array<String>) {

    class Person(var firstName: String, var lastName: String) {
        var fullName: String
        init {
            fullName = firstName + " " + lastName
        }
    }

    val person = Person("Sam", "Gamgee")
    println(person.fullName)  // Sam Gamgee

}
*/

// CLASSES
/*


class MovieList(val genre: String) {
    val movies = ArrayList<String>()

    fun addMovie(movie: String) {
        movies.add(movie)
    }

    fun print() {
        println("Movie List: $genre")
        for (movie in movies) {
            print(movie + " ")
        }
        println()
    }
}

class MovieGoer {
    private var movieList = HashMap<String, MovieList>()
//    fun addList(list: MovieList) {
//        movieList[list.genre] = list
//    }

    fun addGenre(genre: String) {
        movieList[genre] = MovieList(genre)
    }

    fun movieListFor(genre: String) : MovieList? {
        return movieList[genre]
    }

    fun addMovie(genre: String, movie: String) {
        if (!movieList.containsKey(genre)) {
            addGenre(genre)
        }
        movieList[genre]?.movies?.add(movie)
    }
}

fun main(args: Array<String>) {
    val jane = MovieGoer()
    val john = MovieGoer()
//    val actionList = MovieList("Action")
//
//    println(jane.movieList.size) // 0
//
//    jane.addList(actionList)
//    println(jane.movieList) // 1

    jane.addMovie("Action", "Rambo")
    jane.addMovie("Action", "Terminator")

    jane.movieListFor("Action")?.print()

}
*/

// INHERITANCE

/*fun main(args: Array<String>) {
    class Grade(var letter: Char, var points: Double, var credits: Double)

    open class Person(var firstName: String, var lastName: String)

    open class Student(firstName: String, lastname: String, var grades: ArrayList<Grade> = ArrayList()) : Person(firstName, lastname) {
        open fun recordGrade(grade: Grade) {
            grades.add(grade)
        }
    }

    val john = Person("Jonny", "Appleseed")
    val jane = Student("Jonny", "Appleseed")
    val history = Grade('B',9.0,3.0)
    jane.recordGrade(history)


    open class BandMember(firstName: String, lastName: String) : Student(firstName, lastName) {
        open var minimumPracticeTime = 2
    }

    class OboePlayer(firstName: String, lastName: String): BandMember(firstName,lastName) {
        override  var minimumPracticeTime: Int
            get() {
                return  super.minimumPracticeTime * 2
            }
            set(value) {
                super.minimumPracticeTime   = value / 2
            }

    }

    fun phonebookName(person: Person) : String {
        return "${person.lastName}, ${person.firstName}"
    }
    val person = Person("Johnny", "Appleseed")
    var oboePlayer = OboePlayer("Jane", "Appleseed")

    println(phonebookName(person))
    println(phonebookName(oboePlayer))

    var hallMonitor = Student("Jill", "Bananapeel")
    hallMonitor = oboePlayer

    (oboePlayer as BandMember).minimumPracticeTime = 4

    (hallMonitor as? BandMember)?.let {
        println(""" This hall monitor is a band memberand practices at least ${hallMonitor.minimumPracticeTime}""")
    }

    // компілятор сам вибирає яку функцію вибрати відповідно до типу об"єкту
    fun afterClassActivity(student: Student): String {
        return  "Goes to home"
    }
    fun afterClassActivity(student: BandMember): String {
        return  "Goes to practice!"
    }
    println(afterClassActivity(oboePlayer))             // Goes to practice!
    println(afterClassActivity(oboePlayer as Student))  // Goes to home

    class StudentAthlete(firstName: String, lastName: String): Student(firstName, lastName) {
        var failedClasses = ArrayList<Grade>()

        override fun  recordGrade(grade: Grade) {
            super.recordGrade(grade)

            if (grade.letter == 'F') {
                failedClasses.add(grade)
            }
        }

        var isEligible: Boolean = true
            get() {
                return failedClasses.size < 3
            }
    }

}*/

// DATA CLASSES
/*
fun main(args: Array<String>) {

    data class Podcast(val title: String, val description: String, val url: String)
    val podcast = Podcast("Android Central", "the premier source for weekly news",
            "https://feeds.feedburner.com")
    val podcast2 = podcast.copy("Developers Backstage")
    val (title, description, url) = podcast2
    println("title = $title, url = $url") // title = Developers Backstage, url = https://feeds.feedburner.com

    data class Student(val firstName: String,
                       val lastname: String,
                       val grade: Char)
    data class Student2(val firstName: String,
                       val lastname: String,
                       val grade: Char = 'A')

    var sam = Student("Sam","Gamgee", 'A')
    println(sam)  // Student(firstName=Sam, lastname=Gamgee, grade=A)

    sam = sam.copy(grade = 'B')
    println(sam)  // Student(firstName=Sam, lastname=Gamgee, grade=B)

    val fred = Student2("Fred", "Smith")
    println(fred)  // Student2(firstName=Fred, lastname=Smith, grade=A)

}
*/


/*

sealed class Expression

data class Num(val number: Double) : Expression()
data class Sum(val e1: Expression, val e2 : Expression) : Expression()
object NotNumber : Expression()

fun eval(expr: Expression) : Double = when(expr) {
    is Num -> expr.number
    is Sum -> eval(expr.e1) + eval(expr.e2)
    NotNumber -> Double.NaN
}

fun main(args: Array<String>) {
    val num1 = Num(5.5)
    val num2 = Num(10.0)
    println("the sum of 5.5 and 10.0 is ${eval(Sum(num1, num2))}")
}*/


// SINGLETON
/*
object MySingleton {
    fun doMyStuff(data: String) {
        println("This is my data $data ")
    }

    val myConstant = "This my Constant"
}

//-----------------

data class Student(val firstname: String, val lastName: String)

object StudentRegistry {
    val allStudents = mutableListOf<Student>()

    fun addStudent(student: Student) {
        allStudents.add(student)
    }
    fun removeStudent(student: Student) {
        allStudents.remove(student)
    }
}

fun main(args: Array<String>) {

    MySingleton.doMyStuff("Hello there" + MySingleton.myConstant)

    //---------

    val steve = Student("Steve", "Miller")
    val john = Student("John", "Smith")
    StudentRegistry.addStudent(steve)
    StudentRegistry.addStudent(john)
    StudentRegistry.allStudents.forEach {
        println(it.firstname + " " + it.lastName)
    }

}
*/

// COMPANION OBJECT - викликається ClassName.object  - як функція класу чи статична функція

/*
class Student private constructor() {
    var firstName: String? = null
    var lastName: String? = null

    companion object Loader {
        fun loadStudent(jsonText: String) : Student {
            val student = Student()
            student.firstName = "FirstName"
            student.lastName = "lastName"
            return student
        }
    }

}

fun main(args: Array<String>) {
    val student = Student.loadStudent("JSON Text")
    println(student.firstName)

}*/

// INTERFACE

/*
interface Animal {
    fun eat()
    val numLegs : Int
}
class Dog : Animal {
    override val numLegs: Int = 4
    override fun eat() {
        println("Dog eating Loudly")
    }
}
class Cat: Animal {
    override val numLegs: Int = 4
    override fun eat() {
        println("Cat eating Softly")
    }

    fun meow() {
        println("Meow, Meow")
    }
}
//----------

interface Shape {
    var width: Int
    var height: Int
    fun draw()
}
class Circle(override var width: Int, override var height: Int) : Shape {
    override fun draw() {
        println("Drawing a Circle")
    }
}
class Line(override var width: Int, override var height: Int) : Shape {
    override fun draw() {
        println("Drawing a Line")
    }
}
fun printShape(shape: Shape) {
    shape.draw()
}

fun main(args: Array<String>) {
    val dog = Dog()
    val cat = Cat()

    dog.eat()
    cat.eat()
    cat.meow()

    //-----
    val circle = Circle(10,10)
    val line = Line(10,10)
    printShape(circle)  // Drawing a Circle
    printShape(line)    // Drawing a Line

}
*/

// ENUM

/*enum class Direction {
    NORTH, SOUTH, WEST, EAST;
    fun printDirection() {
        println("I am going $this")
    }
}

enum class Color(val r: Int, g: Int, b: Int) {
    RED(255,0,0),
    YELLOW(255,255,0),
    GREEN(0,255,0)

}

fun drive(direction: Direction) {
    when (direction) {
        Direction.EAST -> println("Driving East")
        Direction.NORTH -> println("Driving North")
        Direction.SOUTH -> println("Driving South")
        Direction.WEST -> println("Driving West")
    }
}

fun main(args: Array<String>) {
    drive(Direction.NORTH)  // Driving North
    drive(Direction.WEST)   // Driving West
    println(Direction.WEST.ordinal)  // порядковий номер  - 2
    println(Direction.WEST.printDirection()) // I am going WEST
}*/

// EXTENSION
/*

//fun String.lastChar() : Char = this.get(this.length-1)
import utils.lastChar
// для того щоб робити  utils.lastChar - треба створити file strings.kt,
// папку src : New -> Package
// тоді import utils.lastChar
//----------

class MovieList(val genre: String) {
    val movies = ArrayList<String>()

    fun addMovie(movie: String) {
        movies.add(movie)
    }

    fun print() {
        println("Movie List: $genre")
        for (movie in movies) {
            print(movie + " ")
        }
        println()
    }
}

val MovieList.movieSize : Int  //ми можемо перевизначити, але ініціалізатори не допускаються
    get() = movies.size

fun main(args: Array<String>) {

    println("The last character is ${"MyText".lastChar()}")

}
*/


// GETTER AND SETTER  , THIS

    // VISIBILITY MODIFIERS: private, protected, internal, public

/*class Person {
    var firstName = ""
    var child = Child()

    fun setFirst(firstName: String) {
        this.firstName = firstName
    }

    inner  class Child {
        var firstName = ""

        fun printParentage() {
            println("Child ${this@Child.firstName} with parent ${this@Person.firstName}")
        }

    }
}
fun main(args: Array<String>) {
    val person = Person()
    person.firstName = "Sam"
    person.child.firstName = "Suzzy"
    person.child.printParentage()

}*/


import kotlin.properties.Delegates

data class Course(var className: String) {
    val courseDescription: String by lazy {
        "Course ${className} taught by $teacherName"
    }

    private lateinit var teacherName: String

    var room: String by Delegates.observable("No Room") {
        property, oldValue, newValue ->  println("New value is $newValue")
    }

    fun setTacher(teacher: String) {
        teacherName = teacher
    }
}

fun main(args: Array<String>) {
    val course = Course("Match")
    course.setTacher("Ms Price")
    course.room = "Library"
    println("Course Description ${course.courseDescription}")
    println("Course is ${course}")
}










