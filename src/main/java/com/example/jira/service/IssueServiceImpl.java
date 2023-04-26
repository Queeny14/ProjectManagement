package com.example.jira.service;

import com.example.jira.dao.IssueRepository;
import com.example.jira.entity.Issue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class IssueServiceImpl implements IssueService{

    @Autowired
    private IssueRepository issueRepository ;

    @Autowired
    public IssueServiceImpl(IssueRepository theIssueRepository) {
        issueRepository = theIssueRepository;
    }

    @Override
    public List<Issue> findAll() {
        return issueRepository.findAll();
    }

    @Override
    public Issue findById(int theId) {
        Optional<Issue> result = issueRepository.findById(theId);

        Issue theIssue = null;

        if (result.isPresent()) {
            theIssue = result.get();
        }
        else {

            throw new RuntimeException("Did not find issue id - " + theId);
        }
        return theIssue;
    }

    @Override
    public void save(Issue theIssue) {
        issueRepository.save(theIssue) ;
    }

    @Override
    public Issue updateIssue(int theId, Issue issueDetails) {
       Issue issue =  issueRepository.findById(theId).orElseThrow(() -> new RuntimeException("Issue does not exist"));
        issue.setDescription(issueDetails.getDescription());
        issue.setPriority(issueDetails.getPriority());
        issue.setSprint(issueDetails.getSprint());
        issue.setStatus(issueDetails.getStatus());
        issue.setSummary(issueDetails.getSummary());
        issue.setUpdatedDate(issueDetails.getUpdatedDate());
        issue.setAssignedTo(issueDetails.getAssignedTo());
        issue.setReportTo(issueDetails.getReportTo());
        return issueRepository.save(issue);
    }

    @Override
    public void deleteById(int theId) {
        issueRepository.deleteById(theId);
    }
}
