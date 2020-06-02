# Author: Surayez Rahman
# Created: 2nd June 2020

import requests

# Create two users
createUser1 = requests.post('http://localhost:8080/user?email=surayez%40test.com&first_name=Surayez&last_name=Rahman')
createUser2 = requests.post('http://localhost:8080/user?email=ishan%40test.com&first_name=Ishan&last_name=Joshi')
print("SUCCESSFULLY Created 2 default users\n")

# Create two blogs
blog1 = requests.post('http://localhost:8080/blog?blog=Lorem%20ipsum%20dolor%20sit%20amet%2C%20consectetur%20adipiscing%20elit%2C%20sed&userId=1')
blog2 = requests.post('http://localhost:8080/blog?blog=Lorem%20ipsum%20dolor%20sit%20amet%2C%20consectetur%20adipiscing%20elit%2C%20sed&userId=2')
blog3 = requests.post('http://localhost:8080/blog?blog=Lorem%20ipsum%20dolor%20sit%20amet%2C&email=annonymous%40test.com&first_name=annonymous&last_name=test')
print("SUCCESSFULLY Created 2 default blogs\n")

# Create default threaded comments
comment1 = requests.post('http://localhost:8080/comment?blogId=1&commentText=FirstComment_Blog1%20Comment%20text&email=sample1%40test.com&name=sample1')
comment2 = requests.post('http://localhost:8080/comment?blogId=2&commentText=FirstComment_Blog2%20Comment%20text&email=sample1%40test.com&name=sample1')
# threaded comments for first comment
threadedComment1 = requests.post('http://localhost:8080/comment?blogId=1&commentText=ThreadedComment_Parent1%20Comment1&email=sample%40test.com&name=childSample&parent=1')
threadedComment2 = requests.post('http://localhost:8080/comment?blogId=1&commentText=ThreadedComment_Parent3%20Comment1&email=sample%40test.com&name=childSample&parent=3')
threadedComment3 = requests.post('http://localhost:8080/comment?blogId=1&commentText=ThreadedComment_Parent4%20Comment1&email=sample%40test.com&name=childSample&parent=4')
print("SUCCESSFULLY Created multi-threaded comments\n")