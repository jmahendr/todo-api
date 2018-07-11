package info.joshuastech.springboot.course;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourseService {
	
	@Autowired
	private CourseRepository courseRepository;
	
	public List<Course> getAllCourses(String topicId) {
		//Id is for the topic, need to get all courses under the topic id provided.
		List<Course> courses = new ArrayList<>();
		
		courseRepository.findByTopicId(topicId)
			.forEach(courses::add);
		
		return courses;
	}

	public Course getCourse(String id) {
		return courseRepository.findById(id).get();
	}
	
	public void addCourse(Course topic) {
		courseRepository.save(topic);
	}

	public void updateCourse(Course course) {
		courseRepository.save(course);
		
	}

	public void  deleteCourse(String id) {
		courseRepository.deleteById(id);
		
	}
}
