package com.example.jira.service;

import com.example.jira.dao.WorkflowRepository;
import com.example.jira.entity.Workflow;
import com.example.jira.exception.CustomNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class WorkflowServiceImpl implements WorkflowService{

    @Autowired
    private WorkflowRepository workflowRepository;
    @Override
    public List<Workflow> findAll() {
        return workflowRepository.findAll();
    }

    @Override
    public Workflow findById(int theId) {
        Optional<Workflow> result = workflowRepository.findById(theId);

        if(result.isEmpty()){
            throw new CustomNotFoundException("Requested Workflow does not exist");
        }

        Workflow theWorkflow = null;
        theWorkflow = result.get();
        return theWorkflow;
    }



    @Override
    public void save(Workflow theWorkflow) {
        workflowRepository.save(theWorkflow);
    }

    public Workflow updateWorkflow(int theId, Workflow workflowDetails){
        Workflow workflow =  workflowRepository.findById(theId).orElseThrow(() -> new RuntimeException("Workflow does not exist"));
        workflow.setDescription(workflowDetails.getDescription());
        workflow.setSteps(workflowDetails.getSteps());
        return workflowRepository.save(workflow);

    }

    @Override
    public void deleteById(int theId) {
        workflowRepository.deleteById(theId);

    }
}
