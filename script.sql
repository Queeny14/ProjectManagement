
CREATE TABLE IF NOT EXISTS projects (
  id INT(11) NOT NULL AUTO_INCREMENT,
  name VARCHAR(255) NOT NULL,
  description TEXT,
  start_date DATE,
  end_date DATE,
  project_manager VARCHAR(255),
  PRIMARY KEY (id)
);
CREATE TABLE IF NOT EXISTS users (
  id INT(11) NOT NULL AUTO_INCREMENT,
  fullname VARCHAR(50) NOT NULL,
  email VARCHAR(255) NOT NULL,
  password VARCHAR(50) NOT NULL,
  role VARCHAR(50),
  PRIMARY KEY (id)
);

-- Issues Table
CREATE TABLE IF NOT EXISTS issues (
  id INT(11) NOT NULL AUTO_INCREMENT,
  project_id INT(11) NOT NULL,
  report_to INT(11) NOT NULL,
  assignee INT(11) NOT NULL,
  label ENUM('Epic', 'Bugfix', 'Story', 'Task') DEFAULT 'Epic',
  summary VARCHAR(255) NOT NULL,
  description TEXT,
  priority ENUM('Critical', 'Major', 'Minor') DEFAULT 'Major',
  status VARCHAR(50),
  created_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  updated_date TIMESTAMP,
  sprint VARCHAR(50),
  PRIMARY KEY (id),
  FOREIGN KEY (project_id) REFERENCES projects(id) ON DELETE CASCADE ON UPDATE CASCADE,
  FOREIGN KEY (report_to) REFERENCES users(id) ON DELETE CASCADE ON UPDATE CASCADE,
  FOREIGN KEY (assignee) REFERENCES users(id) ON DELETE CASCADE ON UPDATE CASCADE
);

-- Tasks Table
-- CREATE TABLE IF NOT EXISTS tasks (
--   id INT(11) NOT NULL AUTO_INCREMENT,
--   issue_id INT(11) NOT NULL,
--   assignee_id INT(11) NOT NULL,
--   reporter_id INT(11) NOT NULL,
--   description TEXT NOT NULL,
--   status VARCHAR(50),
--   estimated_time VARCHAR(50),
--   PRIMARY KEY (id),
--   FOREIGN KEY (issue_id) REFERENCES issues(id) ON DELETE CASCADE ON UPDATE CASCADE,
--   FOREIGN KEY (assignee_id) REFERENCES users(id) ON DELETE CASCADE ON UPDATE CASCADE,
--   FOREIGN KEY (reporter_id) REFERENCES users(id) ON DELETE CASCADE ON UPDATE CASCADE
-- );

-- Users Table


-- Comments Table
CREATE TABLE IF NOT EXISTS comments (
  id INT(11) NOT NULL AUTO_INCREMENT,
  author_id INT(11) NOT NULL,
  issue_id INT(11) NOT NULL,
  comment TEXT NOT NULL,
  created_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (id),
  FOREIGN KEY (issue_id) REFERENCES issues(id) ON DELETE CASCADE ON UPDATE CASCADE,
  FOREIGN KEY (author_id) REFERENCES users(id) ON DELETE CASCADE ON UPDATE CASCADE
  
);

-- Attachments Table
CREATE TABLE IF NOT EXISTS attachments(
  id INT(11) NOT NULL AUTO_INCREMENT,
  attached_by INT(11) NOT NULL,
  issue_id INT(11) NOT NULL,
  filename VARCHAR(255) NOT NULL,
  file_type VARCHAR(50) NOT NULL,
  file_size INT(11) NOT NULL,
  file_path VARCHAR(255) NOT NULL,
  created_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (id),
  FOREIGN KEY (attached_by) REFERENCES users(id) ON DELETE CASCADE ON UPDATE CASCADE,
  FOREIGN KEY (issue_id) REFERENCES issues(id) ON DELETE CASCADE ON UPDATE CASCADE
);

-- Workflow Table
CREATE TABLE IF NOT EXISTS workflow (
  id INT(11) NOT NULL,
  issue_id INT(11) NOT NULL,
  name VARCHAR(255) NOT NULL,
  description TEXT,
  steps TEXT,
  FOREIGN KEY (issue_id) REFERENCES issues(id) ON DELETE CASCADE ON UPDATE CASCADE
);

-- History Table
CREATE TABLE IF NOT EXISTS history (
  id INT(11) NOT NULL AUTO_INCREMENT,
  issue_id INT(11) NOT NULL,
  field_name VARCHAR(255) NOT NULL,
  changed_by INT(11) NOT NULL,
  changed_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (id),
  FOREIGN KEY (issue_id) REFERENCES issues(id) ON DELETE CASCADE ON UPDATE CASCADE,
  FOREIGN KEY (changed_by) REFERENCES users(id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE IF NOT EXISTS users_projects (
  user_id INT(11) NOT NULL,
  project_id INT(11) NOT NULL,
  FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE ON UPDATE CASCADE,
  FOREIGN KEY (project_id) REFERENCES projects(id) ON DELETE CASCADE ON UPDATE CASCADE
);