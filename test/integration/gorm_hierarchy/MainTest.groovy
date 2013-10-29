package gorm_hierarchy

import org.junit.Test

import static org.junit.Assert.assertEquals

class MainTest {

    @Test
    void test_basic_behaviour() {
        Driver driverShort = new DriverShort(distanceToTheFloor: 3)
        Driver driverTall = new DriverTall(distanceToTheRoof: 5)
        driverShort.save()
        driverTall.save()

        Car carShort = new Car(color: 'blue', driver: driverShort)
        Car carTall = new Car(color: 'red', driver: driverTall)

        carShort.save()
        carTall.save()

        assertEquals(2, Car.findAll().size())
        assertEquals(2, Driver.findAll().size())
        assertEquals(1, DriverShort.findAll().size())
        assertEquals(1, DriverTall.findAll().size())

        println 'Cars'
        Car.findAll().each {
            println "${it.id} ${it.color} ${it.driver.getDistance()}"
        }

        println 'Drivers'
        Driver.findAll().each {
            println "id: ${it.id} distance: ${it.getDistance()} class: ${it.getDomainClass()}"
        }

        println 'DriversShort'
        DriverShort.findAll().each {
            println "id: ${it.id} distance: ${it.getDistance()}"
        }

        println 'DriversTall'
        DriverTall.findAll().each {
            println "id: ${it.id} distance: ${it.getDistance()}"
        }

        Car.findAll()*.delete()
        assertEquals(0, Car.findAll().size())

        Driver.findAll()*.delete()
        assertEquals(0, Driver.findAll().size())
        assertEquals(0, DriverShort.findAll().size())
        assertEquals(0, DriverTall.findAll().size())
    }
}
