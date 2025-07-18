package com.njust.springboot.service.impl;

import com.njust.springboot.entity.Discussion;
import com.njust.springboot.mapper.DiscussionMapper;
import com.njust.springboot.service.DiscussionService;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import java.util.List;

@Service
public class DiscussionServiceImpl implements DiscussionService {
    @Resource
    private DiscussionMapper discussionMapper;

    @Override
    public int addDiscussion(Discussion discussion) {
        return discussionMapper.insert(discussion);
    }

    @Override
    public int deleteDiscussion(Long id) {
        return discussionMapper.deleteById(id);
    }

    @Override
    public int updateDiscussion(Discussion discussion) {
        return discussionMapper.update(discussion);
    }

    @Override
    public Discussion getDiscussionById(Long id) {
        return discussionMapper.selectById(id);
    }

    @Override
    public List<Discussion> getAllDiscussions() {
        return discussionMapper.selectAll();
    }
} 