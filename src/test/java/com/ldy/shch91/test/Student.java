package com.ldy.shch91.test;

import java.io.Serializable;

public  class Student<T>  implements Serializable {
        private int id;
        private String name;
        private T  course;

        public Student(){}

        public  Student(int id,String name,T t){
            this.id=id;
            this.name=name;
            this.course=t;
        }
        public int getId() {
            return id;
        }
        public void setId(int id) {
            this.id = id;
        }
        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }
        public T getCourse() {
            return course;
        }
        public void setCourse(T course) {
            this.course = course;
        }

    }